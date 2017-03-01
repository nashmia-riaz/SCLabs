using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;
using System.Dynamic;


namespace SoftwareConstruction_Lab1
{
    public partial class Form1 : Form
    {
        //Channel class
        public class Channel
        {
            public String link;
            public String channelDetails;
            public Channel nextChannel;

            //channel constructors
            public Channel()
            {
                link = null;
                nextChannel = null;
            }				//end constructor

            public Channel(String value, Channel newChannel)
            {
                this.link = value;
                this.nextChannel = newChannel;
                this.channelDetails = "blah blah";
            }				//end constructor
        }

        //tv manager class
        public class TVManager
        {
            public Channel head;

            public TVManager()
            {
                head = null;
            }

            //add channel function
            public void addChannel(string path)
            {
                Channel temp = new Channel(path, null);
                temp.nextChannel = null;
                //if list is empty
                if (head == null)
                {
                    head = temp;
                }
                //if list is full
                else
                {
                    Channel x = new Channel();
                    for (x = head; x != null; x = x.nextChannel)
                    {
                        if (x.nextChannel == null)
                        {
                            x.nextChannel = temp;
                            break;
                        }
                    }
                }
            }

            public void replaceChannel(string exPath, string newPath)
            {
                //traverse list
                for (Channel x = head; x != null; x = x.nextChannel)
                {
                    //replace link
                    if (x.link == exPath)
                    {
                        x.link = newPath;
                    }
                }
            }

            public void removeChannel(string path)
            {
                Channel temp = new Channel();
                for (Channel x = head; x != null; x = x.nextChannel)
                {
                    if (x.link == path)
                    {
                        if (x.nextChannel == null)
                        {
                            temp.nextChannel = null;
                        }
                        else if (x == head)
                        {
                            if (x.nextChannel != null)
                            {
                                head = x.nextChannel;
                            }
                            else {

                                head = null;
                            }
                        }
                        else
                        {
                            temp.nextChannel = x.nextChannel;
                        }
                        break;
                    }

                    temp = x;
                }
            }

            public string printChannel()
            {
                string links="";
                for (Channel x = head; x != null; x = x.nextChannel)
                {
                    links +=x.channelDetails;
                }
                return links;

            }
        }


        public TVManager myManager = new TVManager();
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void addChannelButton_Click(object sender, EventArgs e)
        {
            myManager.addChannel(channelLinkAdd.Text);
            
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void channelLinkAdd_TextChanged(object sender, EventArgs e)
        {

        }

        private void replaceChannelButton_Click(object sender, EventArgs e)
        {
            myManager.replaceChannel(textBox1.Text, textBox2.Text);
        }

        private void deleteChannelButton_Click(object sender, EventArgs e)
        {
            myManager.removeChannel(textBox4.Text);
        }

        private void getDescriptionButton_Click(object sender, EventArgs e)
        {
            label5.Text = myManager.printChannel();
        }

        private void watchVideoButton_Click(object sender, EventArgs e)
        {

        }

        private void label5_Click(object sender, EventArgs e)
        {

        }

        private void axShockwaveFlash1_Enter(object sender, EventArgs e)
        {

        }
    }

    


}
