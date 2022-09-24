This is repository for SuperCollider patch which allows to control synthesizers written in faust with two gamepads, playing ~8 octaves chromatically and simultaneously controll 4 parameters which may change gain, pitch and timbre.

0. How to use it?

In order to use Langley, you need to have SuperCollider installed, and copy Extensions and Instruments folders into your SuperCollider extensions directory.
#TODO make a script to do this automatically

Then you need to change some lines of code to use your own gamepads instead of the same ones as me.

Gamepads are contacting with computer by HID protocol. After connecting them to your computer, use SuperCollider command:

HID.findAvailable;

Which should print you something like this:

HID: found 2 devices
-> IdentityDictionary[ (1 -> a HIDInfo(DragonRise Inc.  , Generic   USB  Joystick  , IDs:121, 6, /dev/hidraw0, , 263, 0)), (0 -> a HIDInfo(SHANWAN, Trust Gamepad, IDs:5215, 453, /dev/hidraw1, , 512, 0)) ]

We are interested in IDs and names of gamepads. In Extensions/lang.sc change those lines:

		~myhid  = HID.open( 121, 6 );
		~myhid2 = HID.open( 5215, 453 );

With IDs of your devices instead of 121, 6 and 5215, 453.
After using HID.open() method, SuperCollider is litening for HID messages from our gamepads. Extensions/setHID.sc describes what it will do after getting one. You should change all lines:

			deviceName: "Generic   USB  Joystick  ",

and:

			deviceName: "Trust Gamepad",

To names of your devices.
#TODO make some aliases so it will be easier, or even better, automate the whole thing

Then recompile class library in SuperCollider (or just turn it off and on). After that you need to increase your memory size of the SuperCollider server. In order to do that use those commands:

o = Server.default.options;
o.memSize = 8192 * 10;

And you are good to go. Use this command to start:

Langley.start;

1. How to play?

The A B X Y buttons are changing which synthesizer you are playing. The are stored in rectangular table, which you can see and customize in Extensions/makesynths.sc If you go of the edge, you go back on the opposite side of the table.

For all my my synthesizers, the horizontal axis of left stick controls volume. In my gamepad, most of the physical freedom stick has to move doesn't correspond to change in the value of the parameter given to the computer. Space where stick is actually sensitive is quite small, so you need to control it by barely moving your thumb (but it is totally doable with practice).

Horizontal axis of right stick usually controls the pitch bend. Vertical axes are doing different thing depending on which synth you are using.

The RB, RT, LB and LT buttons (ones usually pressed with index and middle fingers and called shoulder buttons) are controlling actual notes which you are playing. When none of them is pressed, nothing is played. After pressing any combination of them, one note is played, with pitch dependend on the combination.

Put one gamepad on top of the second one (you may want to secure this position, by tying them together). Index and middle fingers are going on RB, RT, LB and LT buttons of the top gamepad, ring and pinky fingers are going on the same buttons on the botton gamepad.

There is a coded frequency of 13.75 Hz (you can change it in Extensions/lang.sc it is class variable aroundFreq). Every shoulder button change this frequency by specific interval.

In top gamepad:
RB - minor second (1 half-step)
LB - major second (2 half-steps)
RT - major third  (4 half-steps)
LT - minor sixth  (8 half-steps)

In bottom gamepad:
RB - octave + major third (16 half-steps)
LB - 2 octaves + minor sixth (32 half-steps)
RT - 5 octaves + major third (64 half-steps)
LT - quarter tone (1/2 half-step)

So, for example, if one would like to play A-moll scale starting with A=440Hz:

A  H  C  D  E  F  G
00 10 11 01 11 00 01
11 11 11 00 00 01 01
11 11 11 00 00 00 00
00 00 00 01 01 01 01

Where 00 corresponds to TLT TRT, where BLT corresponds to LT of bottom gamepad
      11                TLB TRB
      11                BLT BRT
      00                BLB BLB

0 corresonds to depressed button and 1 to pressed button.

Other buttons don't do anything (yet).

1.1 FAQ
	1.1.0 Isn't this set up of mapping buttons to notes counterintuitive?
	
	You are free to make your own one, and add it to Extensions/intHID.sc
	#TODO make a tutorial how to do it
	(A personal opinion - while I would agree, that it is much less beginner friendly then guitar or piano, I would argue that it is easier than trumpet or violin)

	1.1.1 Why would I use it instead of normal MIDI controler?

	With normal MIDI controler you normally use one hand to play notes, and the whole second hand to control stick with two degrees of freedom. Using gamepad you can control two sticks with just thumbs, with four degrees of freedom. Also, different instruments have different flavors based on scales which are easy to play on them. For example, keyboards are designed so C-dur and A-moll are easy to play, on Langley on the other hand whole-tone scale is easier to play than diatonic ones (just don't use RT button on the top gamepad!). Huge interval jumps (bigger than octave) should also be easier than with one-handed keyboard playing.

	1.1.2 Notes are playing while no shoulder buttons are pressed. What is happening and how can I stop it?

	You probably changed synth while playing a note, so a previous one didn't receive a HID message to end a note. Try playing any other note on a previous synth.
	#TODO explain it in more detail, as it may by used intentionally to play drone. Also write a command to stop all sounds automatically

2. How to add synths? Can I use third party plugins in VST or LV2 format?	

#TODO this section


