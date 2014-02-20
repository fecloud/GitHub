/**
 * @(#) CreateOrSelectDialog.java Created on 2012-8-7
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.aspire.common.util.StringConstants;
import com.aspire.sqmp.mobilemanager.listener.DialogClickListener;

import org.eclipse.swt.widgets.Label;

/**
 * The class <code>CreateOrSelectDialog</code>
 * 
 * @author likai
 * @version 1.0
 */
public class CreateOrSelectDialog extends Dialog {

    public static final int CREATE_BUTTON = 2000;
    public static final int SELECT_BUTTON = 2001;

    private DialogClickListener clickListener;

    private Composite container;

    /**
     * Setter of clickListener
     * 
     * @param clickListener
     *            the clickListener to set
     */
    public void setClickListener(DialogClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public CreateOrSelectDialog(Shell parentShell) {
        super(parentShell);
        setShellStyle(SWT.MAX | SWT.RESIZE | SWT.TITLE | SWT.PRIMARY_MODAL);
    }

    /**
     * 
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText(StringConstants.PROMPT);
    }

    /**
     * Create contents of the dialog.
     * 
     * @param parent
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        container = (Composite) super.createDialogArea(parent);
        container.setFont(SWTResourceManager.getFont("微软雅黑", 6, SWT.ITALIC));
        container.setLayout(null);

        Label lbln = new Label(container, SWT.NONE);
        lbln.setBounds(26, 10, 249, 73);
        lbln.setText("发现新设备！如需创建请点击“新建”；" + "\n" + "如需关联现有设备，请点击“选择”。");
        return container;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createButtonBar(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, CREATE_BUTTON, "新建", true);
        createButton(parent, SELECT_BUTTON, "选择", false);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.Dialog#buttonPressed(int)
     */
    @Override
    protected void buttonPressed(int buttonId) {
        close();
        clickListener.onDialogButtonClick(buttonId);
    }
}
