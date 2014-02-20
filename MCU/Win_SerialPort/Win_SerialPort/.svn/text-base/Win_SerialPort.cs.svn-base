using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.IO.Ports;

namespace Win_SerialPort_Win7
{
    public partial class Win_SerialPort : Form
    {

        private SerialPort serialport;

        private string setchar,setbote;

        private bool flag;

        private string[] chars = new string[] { "ASCII", "UTF-8", "GBK" };

        private string[] bote = new string[] { "75", "110", "134", "150", "300", "600", "1200", "1800", "2400", "4800", "7200", "9600", "14400", "19200", "38400", "57600", "115200", "128000"};

        public Win_SerialPort()
        {
            InitializeComponent();

            
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            

            String[] Str2 = System.IO.Ports.SerialPort.GetPortNames();

            if(null != Str2 && Str2.Length > 0)
            {
                Array.Sort<String>(Str2);
                if (Str2.Length > 0)
                {
                    for (int k = 0; k < Str2.Length; k++)
                    {
                        comboBox1.Items.Add(Str2[k]);
                    }
                }
                if (null != Str2)
                {
                    comboBox1.SelectedIndex = 0;
                   
                }
             }

            foreach (string item in chars)
            {
                this.comboBox2.Items.Add(item);
            }
             this.comboBox2.SelectedIndex = 0;
             setchar = this.comboBox2.SelectedItem.ToString();

             foreach (string item in bote)
             {
                 this.comboBox3.Items.Add(item);
             }
             this.comboBox3.SelectedIndex = 11;
             setbote = this.comboBox3.SelectedItem.ToString();
  
        }


        private void button1_Click(object sender, EventArgs e)
        {
            openSerialPort(comboBox1.Text);
            
        }

        private void openSerialPort(string name)
        {
            try
            {
                if (flag)
                {
                    serialport.Close();
                    serialport = null;
                    this.button1.Text = "打开";
                    this.flag = false;
                }
                else
                {
                    serialport = new SerialPort();
                    serialport.BaudRate = Convert.ToInt32(setbote);
                    serialport.PortName = name;
                    serialport.DataBits = 8;
                    serialport.StopBits = StopBits.One;
                    serialport.Open();//打开串口
                    //  serialport.
                    this.flag = true;
                    this.button1.Text = "关闭";
                    serialport.DataReceived += new SerialDataReceivedEventHandler(this.OnDataReceived);
                }
            }catch(Exception ec)
            {
                MessageBox.Show("操作失败！" + ec.Message, "提示！", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }
        private void OnDataReceived(object sender, SerialDataReceivedEventArgs e)
        {
            byte[] buffer = new byte[1024];
            int b = 0;
            try
            {
                //循环接收数据
                while (serialport.BytesToRead > 0)
                {
                    b = serialport.Read(buffer, 0, buffer.Length);
                }
                //在这里对接收到的数据进行处理
                //

                richTextBoxCallBack callback = delegate()
                {
                    string str = Encoding.GetEncoding(setchar).GetString(buffer, 0, b);;
                    this.richTextBox1.Text += str;
                };

                richTextBox1.Invoke(callback);


            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message.ToString());
            }
        }

        private delegate void richTextBoxCallBack();

        private void button2_Click(object sender, EventArgs e)
        {
            send();
        }

        private void send()
        {
            if (serialport != null)
            {

                if (textBox1.Text != null && !textBox1.Text.Trim().Equals(""))
                {
                    byte[] buffer = Encoding.GetEncoding(setchar).GetBytes(textBox1.Text);
                    serialport.Write(buffer, 0, buffer.Length);
                }
            }
            else
            {
                MessageBox.Show("未连接到串口设备！", "提示！", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            richTextBox1.Text = "";
        }

        private void comboBox2_SelectedIndexChanged(object sender, EventArgs e)
        {
            this.setchar = this.comboBox2.SelectedItem.ToString();
        }

        private void textBox1_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (e.KeyChar == (char)13)
            {
                send();
            }
       
        }

        private void comboBox3_SelectedIndexChanged(object sender, EventArgs e)
        {
            this.setbote = comboBox3.SelectedItem.ToString();
        }

    }
}
