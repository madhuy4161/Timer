import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class countDown {
    private JPanel countDown;
    private JButton startButton;
    private JButton pauseButton;
    private JButton resetButton;
    private JLabel seconds;
    private JLabel minutes;
    private JLabel hours;
    private JComboBox minutesinp;
    private JComboBox hoursinp;
    private JComboBox secondsinp;
    private JButton resume;
    int milliseconds_=0;
    static boolean state=true;
    String hh,mm,ss;
    int hours_,minutes_,seconds_;


    public countDown() {
        pauseButton.setEnabled(false);
        resetButton.setEnabled(false);
        resume.setEnabled(false);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                seconds_ = Integer.parseInt(String.valueOf(secondsinp.getSelectedItem()));
                minutes_ = Integer.parseInt(String.valueOf(minutesinp.getSelectedItem()));
                hours_ = Integer.parseInt(String.valueOf(hoursinp.getSelectedItem()));
                resume.setEnabled(false);
                timecontrol();
            }
        });
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = false;
                startButton.setEnabled(true);
                resetButton.setEnabled(true);
                pauseButton.setEnabled(false);
                resume.setEnabled(true);
                startButton.setText("Restart");
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setText("Start");
                startButton.setEnabled(true);
                pauseButton.setEnabled(false);
                resume.setEnabled(false);
                resetButton.setEnabled(false);
                state = false;
                hours_ = 0;
                minutes_ = 0;
                seconds_ = 0;
                hours.setText("00");
                minutes.setText("00");
                seconds.setText("00 ");

            }
        });
        resume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timecontrol();
                resume.setEnabled(false);
                startButton.setEnabled(true);
            }
        });
    }
    public void timecontrol()
    {
        if (seconds_ == 0 && minutes_ == 0 && hours_ == 0) {
            state = false;
        }
        else {
            state = true;
            pauseButton.setEnabled(true);
            Thread t = new Thread() {
                public void run() {
                    for (; ; ) {
                        if (state == true) {
                            try {
                                sleep(9);
                                if (milliseconds_ > 101)
                                {
                                    milliseconds_ = 00;
                                    if(hours_==0 && minutes_==0 && seconds_ !=0)
                                    {
                                        seconds_--;
                                        if(seconds_==0)
                                        {
                                            startButton.setText("START");
                                            startButton.setEnabled(true);
                                            pauseButton.setEnabled(false);
                                            resetButton.setEnabled(false);
                                        }
                                    }
                                    else if(hours_==0 && minutes_ !=0)
                                    {
                                        if(seconds_==0)
                                        {
                                            milliseconds_=0;
                                            seconds_=60;
                                            minutes_--;
                                        }
                                        seconds_--;

                                    }
                                    else if (hours_ !=0 && minutes_ !=0)
                                    {
                                        if(seconds_==0)
                                        {
                                            milliseconds_=0;
                                            seconds_=60;
                                            minutes_--;

                                        }
                                        seconds_--;
                                    }
                                    else if(hours_ !=0 && minutes_ ==0)
                                    {
                                        if(seconds_==0)
                                        {
                                            milliseconds_=0;
                                            seconds_=60;
                                            minutes_=59;
                                            hours_--;

                                        }
                                        seconds_--;
                                    }
                                }
                                milliseconds_++;
                                seconds.setText(" " + seconds_);
                                minutes.setText(" " + minutes_);
                                hours.setText(" " + hours_);
                            } catch (Exception e) {

                            }
                        } else {
                            break;
                        }
                    }

                }
            };
            t.start();
            startButton.setEnabled(false);
            resetButton.setEnabled(true);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("countDown");
        frame.setContentPane(new countDown().countDown);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
