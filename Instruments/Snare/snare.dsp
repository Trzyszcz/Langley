declare name "snare";
declare nvoices "16";

import("stdfaust.lib");

freq = nentry("frequency", 220, 20, 20000, 0.001);
gain = nentry("gain", 1, 0, 1, 0.001);
gate = button("gate");

RightPush = button("RightPush");

//dry_sound = ( (os.lf_imptrain(1) : en.ar(0.001, 0.15)) * gain * no.noise ) : fi.resonhp(220, 6, 1) : fi.lowpass(2, 2000);

dry_sound = ( (gate : en.ar(0.001, 0.15)) * gain * no.noise ) : fi.resonhp(freq, 6, 1) : fi.lowpass(2, 2000);

short_delay = + ~ ( @(0.17*48000) *(7/12) );

wet_sound = 0.2*dry_sound : re.mono_freeverb(0.5, 0.5, 0.5, 0.5);

main_stream = wet_sound + (dry_sound*0.8);

short_delayed_stream = main_stream * RightPush : fi.lowpass(1,900) : short_delay;

process = main_stream + short_delayed_stream<:_,_;
