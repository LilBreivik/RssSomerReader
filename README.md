-> To test the Application, we need to plug the emulator to the internet. 

 The Default-Settings permit a connnection to the Internet, because of 
some weird settings at the DNS-Server Section.

Work-Around: 

Start the emulator from the terminal. 

1.) cd in to the android sdk-emular section (on mac)
/Users/USERNAME/Library/Android/sdk/emulator


2.) ./emulator -avd <YOUR_DEVICE> -dns-server 8.8.8.8
 
