using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace AVR_Contrl
{
    public partial class MainForm : Form
    {
        Serial_Port serial_port;
        public MainForm()
        {
            InitializeComponent();
        }

        private void ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            SettingForm settingForm = new SettingForm();
            settingForm.ShowDialog();
            settingForm.Dispose();
        }

        private void LED_checkBox_CheckedChanged(object sender, EventArgs e)
        {
            byte led_status = 0x00;
            led_status |= Byte.Parse("" + (this.LED_checkBox1.Checked ? 1 : 0));
            led_status |= Byte.Parse("" + (this.LED_checkBox2.Checked ? (1 << 1) : 0));
            led_status |= Byte.Parse("" + (this.LED_checkBox3.Checked ? (1 << 2) : 0));
            led_status |= Byte.Parse("" + (this.LED_checkBox4.Checked ? (1 << 3) : 0));
            led_status |= Byte.Parse("" + (this.LED_checkBox5.Checked ? (1 << 4) : 0));
            led_status |= Byte.Parse("" + (this.LED_checkBox6.Checked ? (1 << 5) : 0));
            led_status |= Byte.Parse("" + (this.LED_checkBox7.Checked ? (1 << 6) : 0));
            led_status |= Byte.Parse("" + (this.LED_checkBox8.Checked ? (1 << 7) : 0));
            Contrl_LED(led_status);
        }

        private bool OpenSerial_port()
        {
            if (null == serial_port)
            {
                serial_port = new Serial_Port();
                serial_port.Name = Properties.Settings.Default.Setting_serial_no;
                serial_port.Baud = Convert.ToInt32(Properties.Settings.Default.Setting_baud);
                serial_port.Charset = Properties.Settings.Default.Setting_charset;
            }

            return serial_port.Open();
        }
        /// <summary>
        /// 控制LED灯
        /// </summary>
        /// <param name="b"></param>
        private void Contrl_LED(byte b)
        {

            DateTime now = DateTime.Now;
            bool sender = new Command_Execute(serial_port).ExcuteCommand(new AVR_Command(AVR_Command.LED, b));
            System.Threading.Thread.Sleep(100);
            byte recevie = serial_port.Recevie();
            if (sender && recevie == AVR_Command.AVR_TRUE)
            {
                toolStripStatusLabel1.Text = "LED发送成功！ " + now.ToLocalTime();
            }
            else
            {
                toolStripStatusLabel1.Text = "LED发送失败！ " + now.ToLocalTime();
            }

        }

        private void MainForm_Load(object sender, EventArgs e)
        {
            OpenSerial_port();
        }

        private void buzzer_btn_MouseDown(object sender, MouseEventArgs e)
        {
            DateTime now = DateTime.Now;
            bool send = new Command_Execute(serial_port).ExcuteCommand(new AVR_Command(AVR_Command.Buzzer, AVR_Command.AVR_TRUE));
            System.Threading.Thread.Sleep(100);
            byte recevie = serial_port.Recevie();
            if (send && recevie == AVR_Command.AVR_TRUE)
            {
                toolStripStatusLabel1.Text = "打开蜂鸣器发送成功！ " + now.ToLocalTime();
            }
            else
            {
                toolStripStatusLabel1.Text = "打开蜂鸣器发送失败！ " + now.ToLocalTime();
            }
        }

        private void buzzer_btn_MouseUp(object sender, MouseEventArgs e)
        {
            DateTime now = DateTime.Now;
            bool send = new Command_Execute(serial_port).ExcuteCommand(new AVR_Command(AVR_Command.Buzzer, AVR_Command.AVR_FlASE));
            System.Threading.Thread.Sleep(100);
            byte recevie = serial_port.Recevie();
            if (send && recevie == AVR_Command.AVR_TRUE)
            {
                toolStripStatusLabel1.Text = "关闭蜂鸣器发送成功！ " + now.ToLocalTime();
            }
            else
            {
                toolStripStatusLabel1.Text = "关闭蜂鸣器发送失败！ " + now.ToLocalTime();
            }
        }

        private void MainForm_Activated(object sender, EventArgs e)
        {
            OpenSerial_port();
        }

        /// <summary>
        /// 发送4位数码管
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void LED_SD_button_Click(object sender, EventArgs e)
        {
            if (!String.IsNullOrEmpty(LED_SD_textBox.Text))
            {
                DateTime now = DateTime.Now;
                bool send = new Command_Execute(serial_port).ExcuteCommand(new AVR_Command(AVR_Command.LED_SD, Convert.ToInt32(LED_SD_textBox.Text)));
                System.Threading.Thread.Sleep(100);
                byte recevie = serial_port.Recevie();
                if (send && recevie == AVR_Command.AVR_TRUE)
                {
                    toolStripStatusLabel1.Text = "4位数码管显示成功！ " + now.ToLocalTime();
                }
                else
                {
                    toolStripStatusLabel1.Text = "4位数码管显示失败！ " + now.ToLocalTime();
                }
            }
        }





    }
}
