using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using System.IO.Ports;

namespace AVR_Contrl
{
    class Serial_Port
    {
        private string name;

        public string Name
        {
            get { return name; }
            set { name = value; }
        }

        private int baud;

        public int Baud
        {
            get { return baud; }
            set { baud = value; }
        }

        private string charset;

        public string Charset
        {
            get { return charset; }
            set { charset = value; }
        }

        private bool flag;

        private SerialPort serialport;

        public SerialPort Serialport
        {
            get { return serialport; }
            set { serialport = value; }
        }
        /// <summary>
        /// 打开串口
        /// </summary>
        /// <returns></returns>
        public bool Open()
        {
            try
            {
                if (flag)
                {

                }
                else
                {
                    serialport = new SerialPort();
                    serialport.BaudRate = baud;
                    serialport.PortName = name;
                    serialport.DataBits = 8;
                    serialport.StopBits = StopBits.Two;
                    serialport.Open();//打开串口
                    this.flag = true;
                    //serialport.DataReceived += new SerialDataReceivedEventHandler(this.OnDataReceived);
                }
            }
            catch (Exception)
            {
                return false;
            }
            return true;
        }

        /// <summary>
        /// 向串口发送数据 
        /// </summary>
        /// <param name="b"></param>
        /// <returns></returns>
        public bool Send(byte b)
        {
            if (Open())
            {
                byte[] bs = new byte[] { b };
                try
                {
                    serialport.Write(bs, 0, bs.Length);
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.Message);

                }

                return true;
            }
            return false;
        }
        /// <summary>
        /// 向串口发送数据 
        /// </summary>
        /// <param name="b"></param>
        /// <returns></returns>
        public bool Sends(byte[] b)
        {
            foreach (byte item in b)
            {
                if (!Send(item))
                {
                    return false;
                }
            }
            return true;
        }

        /// <summary>
        /// 关闭串口
        /// </summary>
        public void Close()
        {
            serialport.Close();
            serialport = null;
            this.flag = false;
        }

        /// <summary>
        /// 读取串口数据
        /// </summary>
        /// <returns></returns>
        public byte Recevie()
        {
            byte[] buffer = new byte[1];
            int b = 0;
            try
            {
                //循环接收数据
                while (serialport.BytesToRead > 0)
                {
                    b = serialport.Read(buffer, 0, buffer.Length);
                }
                //在这里对接收到的数据进行处理

                return buffer[0];

            }
            catch (Exception)
            {
                return 0;
            }
        }

    }

}
