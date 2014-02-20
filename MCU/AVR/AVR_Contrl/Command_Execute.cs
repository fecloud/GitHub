using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AVR_Contrl
{

    class Command_Execute
    {
        private Serial_Port serial_port;

        internal Serial_Port Serial_port
        {
            get { return serial_port; }
            set { serial_port = value; }
        }

        public Command_Execute(Serial_Port serial_port)
        {
            this.serial_port = serial_port;
        }

        public bool ExcuteCommand(AVR_Command cmd)
        {
            if (null != serial_port && null != cmd)
            {
                serial_port.Open();
                serial_port.Sends(cmd.Builder());
            }
            return true;
        }
    }

}
