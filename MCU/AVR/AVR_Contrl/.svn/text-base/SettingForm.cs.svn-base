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
    public partial class SettingForm : Form
    {

        private string[] chars = new string[] { "ASCII", "UTF-8", "GBK" };

        private string[] bauds = new string[] { "75", "110", "134", "150", "300", "600", "1200", "1800", "2400", "4800", "7200", "9600", "14400", "19200", "38400", "57600", "115200", "128000" };



        public SettingForm()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void SettingForm_Load(object sender, EventArgs e)
        {
            String[] serials = System.IO.Ports.SerialPort.GetPortNames();

            if (null != serials && serials.Length > 0)
            {
                Array.Sort<String>(serials);
                foreach (String item in serials)
                {
                    this.seraial.Items.Add(item);
                }


            }

            foreach (string item in bauds)
            {
                this.baud.Items.Add(item);
            }

            foreach (string item in chars)
            {
                this.charset.Items.Add(item);
            }

            Resotre_Data();
        }

        private void Resotre_Data()
        {
            string serial = Properties.Settings.Default.Setting_serial_no;
            string baud = Properties.Settings.Default.Setting_baud;
            string charset = Properties.Settings.Default.Setting_charset;

            if (!String.IsNullOrEmpty(serial))
            {
                this.seraial.SelectedIndex = this.seraial.Items.IndexOf(serial);
            }

            if (!String.IsNullOrEmpty(baud))
            {
                this.baud.SelectedIndex = this.baud.Items.IndexOf(baud);
            }
            if (!String.IsNullOrEmpty(charset))
            {
                this.charset.SelectedIndex = this.charset.Items.IndexOf(charset);
            }
        }

        private void seraial_SelectedIndexChanged(object sender, EventArgs e)
        {
            Properties.Settings.Default.Setting_serial_no = this.seraial.SelectedItem.ToString();

        }

        private void SettingForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            Properties.Settings.Default.Save();
        }

        private void baud_SelectedIndexChanged(object sender, EventArgs e)
        {
            Properties.Settings.Default.Setting_baud= this.baud.SelectedItem.ToString();
        }

        private void charset_SelectedIndexChanged(object sender, EventArgs e)
        {
            Properties.Settings.Default.Setting_charset = this.charset.SelectedItem.ToString();
        }
    }
}
