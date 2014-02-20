/**
 * @(#) AboutDialog.java Created on 2012-7-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import com.aspire.common.util.StringConstants;

/**
 * The class <code>AboutDialog</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public class AboutDialog extends Dialog {

    /**
     * Create the dialog.
     * 
     * @param parentShell
     */
    public AboutDialog(Shell parentShell) {
        super(parentShell);
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
        shell.setText("关于ES系统说明");
    }

    /**
     * Create contents of the dialog.
     * 
     * @param parent
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        container.setLayout(null);

        Group grpes = new Group(container, SWT.NONE);
        grpes.setText("关于ES系统说明");
        grpes.setBounds(28, 10, 397, 195);

        Label lblNewLabel = new Label(grpes, SWT.NONE);
        lblNewLabel.setImage(SWTResourceManager.getImage("C:\\Users\\andyshen\\Desktop\\image001.jpg"));
        lblNewLabel.setBounds(79, 34, 34, 35);

        Label lblNewLabel_2 = new Label(grpes, SWT.WRAP);
        lblNewLabel_2.setBounds(36, 97, 331, 61);
        lblNewLabel_2.setText("      PC管理端是运行在PC上的管理程序，具备从服务端获取拨测脚本、拨测任务和软件版本等资源的功能，并将测试资源导入到终端，从终端获取拨测结果和日志，上传到拨测平台。");

        Label lblVersion = new Label(grpes, SWT.NONE);
        lblVersion.setText("Version 1.0.0 (2012/8/21)");
        lblVersion.setBounds(115, 164, 146, 24);

        return container;
    }

    /**
     * Create contents of the button bar.
     * 
     * @param parent
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, StringConstants.OK, true);
        createButton(parent, IDialogConstants.CANCEL_ID, StringConstants.CANCEL, false);
    }

    /**
     * Return the initial size of the dialog.
     */
    @Override
    protected Point getInitialSize() {
        return new Point(450, 300);
    }
}
