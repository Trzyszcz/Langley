declare name "Shash";
declare nvoices "16";

import("stdfaust.lib");

freq = nentry("freq", 440, 20, 20000, 1);
gain = nentry("gain", 0.3, 0, 10, 0.01): si.smoo;
gate = button("gate");

RightVertical = hslider("rightvertical", 0.5, 0, 1, 0.01) : si.smoo;
LeftVertical = hslider("leftvertical", 0.5, 0, 1, 0.01);

LeftPush = button("LeftPush");
RightPush = button("RightPush");

Envelope = en.adsr(0.1, 0, 1, 0.1);

noilev = 0.001;
pgain = 80;
mspread = 0.0489508 * (2.71828^(1.83413*(2*RightVertical + 1)));

my_sound(xfreq) = (no.noise * noilev <: fi.peak_eq_rm(pgain, xfreq, mspread*0.0001) : fi.lowpass(3, 1.5*xfreq) : fi.highpass(3, 0.5*xfreq));

timbre(x) = my_sound(x) + (1/2) * my_sound(x*2) + (1/3) * my_sound(x*3);

dry_sound = timbre(freq)*(gate : Envelope)*gain;

wet_sound = 0.15*dry_sound : re.mono_freeverb(0.5, 0.5, 0.5, 0.5);

main_stream = wet_sound + (dry_sound*0.85);

long_delay = + ~ ( @(5*48000) *(19/20) );
short_delay = + ~ ( @(0.5*48000) *(1/3) );

short_delayed_stream = main_stream * RightPush : short_delay;
long_delayed_stream = ( main_stream + short_delayed_stream ) * LeftPush : long_delay;

process = main_stream + short_delayed_stream + long_delayed_stream <:_,_;