declare name "Organ";
declare nvoices "16";

import("stdfaust.lib");

freq = nentry("freq", 440, 20, 20000, 0.01);
gain = nentry("gain", 0.3, 0, 10, 0.01) : si.smoo;
gate = button("gate");

RightHorizontal = hslider("righthorizontal", 0.5, 0, 1, 0.01);
RightVertical = hslider("rightvertical", 0.5, 0, 1, 0.01);

LeftPush = button("LeftPush");

Envelope = en.adsr(0.1, 0, 1, 0.1);

Lower_part(freq) = (65/100) * os.triangle(freq*(1/2)*(499/500));

Main_part(freq) = (56/100) * os.osc(freq + (freq*(RightHorizontal*RightVertical)*os.osc(7*freq)));

High_part(freq) = (70/100) * (os.osc(2*freq) + (1/3) * os.osc(3*2*freq) + (1/5) * os.osc(5*2*freq));

dry_sound = (Lower_part(freq) + Main_part(freq) + High_part(freq)) * gain * (gate : Envelope);

wet_sound = 0.15*dry_sound : re.mono_freeverb(0.5, 0.5, 0.5, 0.5);

main_stream = wet_sound + (dry_sound*0.85);

delay = + ~ ( @(5*48000) *(19/20) );

delayed_stream = ( wet_sound + (dry_sound*0.85) ) * LeftPush : delay;

process = main_stream + delayed_stream <:_,_;
