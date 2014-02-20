using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AVR_Contrl
{
    class AVR_Command
    {
        /// <summary>
        /// LED灯
        /// </summary>
        public const byte LED = 0x1;
        /// <summary>
        /// 蜂鸣器
        /// </summary>
        public const byte Buzzer = 0x2;
        /// <summary>
        /// 4位数码管
        /// </summary>
        public const byte LED_SD = 0x3;

        public const byte AVR_TRUE = 0x1;
        public const byte AVR_FlASE = 0x0;

        private byte cmd;

        public byte Cmd
        {
            get { return cmd; }
            set { cmd = value; }
        }

        private int args;

        public int Args
        {
            get { return args; }
            set { args = value; }
        }

        public AVR_Command() { }

        public AVR_Command(byte cmd, int args)
        {
            this.cmd = cmd;
            this.args = args;
        }

        /// <summary>
        /// 封装CMD
        /// </summary>
        /// <returns></returns>
        public byte[] Builder()
        {
            byte[] bs = new byte[6];
            bs[0] = cmd;
            bs[1] = (byte)(args &0xFF);
            bs[2] = (byte)((args & 0xFFFF) >> 8);
            bs[3] = (byte)((args & 0xFFFFFF) >> 16);
            bs[4] = (byte)(args >> 24);
            bs[5] = 1;
            return bs;
        }
    }
}
