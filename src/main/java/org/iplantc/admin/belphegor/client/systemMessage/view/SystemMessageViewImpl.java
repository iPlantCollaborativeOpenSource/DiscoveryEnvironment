package org.iplantc.admin.belphegor.client.systemMessage.view;

import java.util.Date;
import java.util.List;

import org.iplantc.admin.belphegor.client.systemMessage.SystemMessageView;
import org.iplantc.core.resources.client.IplantResources;
import org.iplantc.core.resources.client.messages.IplantDisplayStrings;
import org.iplantc.core.uicommons.client.models.sysmsgs.Message;
import org.iplantc.core.uicommons.client.models.sysmsgs.MessageProperties;

import com.google.common.collect.Lists;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;

public class SystemMessageViewImpl extends Composite implements SystemMessageView, SelectionChangedHandler<Message> {

    private static SystemMessageViewImplUiBinder uiBinder = GWT.create(SystemMessageViewImplUiBinder.class);

    interface SystemMessageViewImplUiBinder extends UiBinder<Widget, SystemMessageViewImpl> {}

    @UiField(provided = true)
    IplantResources res;
    @UiField(provided = true)
    IplantDisplayStrings strings;

    @UiField
    TextButton addBtn, deleteBtn, editBtn;

    @UiField
    Grid<Message> grid;

    @UiField
    ListStore<Message> store;

    private final MessageProperties msgProps;
    private SystemMessageView.Presenter presenter;

    @Inject
    public SystemMessageViewImpl(IplantResources res, IplantDisplayStrings strings, MessageProperties msgProps) {
        this.res = res;
        this.strings = strings;
        this.msgProps = msgProps;
        initWidget(uiBinder.createAndBindUi(this));

        grid.getSelectionModel().addSelectionChangedHandler(this);
        grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onSelectionChanged(SelectionChangedEvent<Message> event) {
        boolean isSingleItemSelected = event.getSelection().size() == 1;
        deleteBtn.setEnabled(isSingleItemSelected);
        editBtn.setEnabled(isSingleItemSelected);
    }

    @UiFactory
    ListStore<Message> createListStore() {
        ListStore<Message> listStore = new ListStore<Message>(msgProps.id());
        return listStore;
    }

    @UiFactory
    ColumnModel<Message> createColumnModel() {
        ColumnConfig<Message, Date> activationDateCol = new ColumnConfig<Message, Date>(msgProps.activationTime(), 200, "Activation Date");
        ColumnConfig<Message, Date> deactivationDateCol = new ColumnConfig<Message, Date>(msgProps.deactivationTime(), 200, "Deactivation Date");
        ColumnConfig<Message, String> msgCol = new ColumnConfig<Message, String>(msgProps.body(), 400, "Message");
        ColumnConfig<Message, String> typeCol = new ColumnConfig<Message, String>(msgProps.type(), 90, "Type");
        activationDateCol.setFixed(true);
        deactivationDateCol.setFixed(true);
        typeCol.setFixed(true);
        typeCol.setAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        
        @SuppressWarnings("unchecked")
        List<ColumnConfig<Message, ?>> colList = Lists.<ColumnConfig<Message, ?>> newArrayList(activationDateCol, deactivationDateCol, msgCol, typeCol);
        
        return new ColumnModel<Message>(colList);
    }

    @UiHandler("addBtn")
    void addButtonClicked(SelectEvent event) {
        final EditCreateSystemMessageDialog createSystemMessage = EditCreateSystemMessageDialog.createSystemMessage(presenter.getAnnouncementTypes());
        createSystemMessage.addOkButtonSelectHandler(new SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                Message msg = createSystemMessage.getMessage();
                presenter.addSystemMessage(msg);
            }
        });
        
        createSystemMessage.show();
    }

    @UiHandler("deleteBtn")
    void deleteBtnClicked(SelectEvent event) {
        presenter.deleteSystemMessage(grid.getSelectionModel().getSelectedItem());
    }

    @UiHandler("editBtn")
    void editBtnClicked(SelectEvent event) {
        // Call out to service to update, update item in store on success callback.
        final EditCreateSystemMessageDialog editSystemMessage = EditCreateSystemMessageDialog.editSystemMessage(grid.getSelectionModel().getSelectedItem(), presenter.getAnnouncementTypes());
        editSystemMessage.addOkButtonSelectHandler(new SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                Message msg = editSystemMessage.getMessage();
                presenter.editSystemMessage(msg);
            }
        });
        editSystemMessage.show();
    }

    @Override
    public void setSystemMessages(List<Message> systemMessages) {
        store.addAll(systemMessages);
    }

    @Override
    public void addSystemMessage(Message systemMessage) {
        store.add(systemMessage);
    }

    @Override
    public void updateSystemMessage(Message updatedSystemMessage) {
        store.update(updatedSystemMessage);
    }

    @Override
    public void deleteSystemMessage(Message msgToDelete) {
        store.remove(msgToDelete);
    }

}
