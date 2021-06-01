declare name "bassdrum";
declare nvoices "16";

import("stdfaust.lib");

freq = nentry("frequency", 440, 20, 20000, 0.001);
gain = nentry("gain", 0.3, 0, 1, 0.001);
gate = button("gate");

dry_sound = (gate : en.ar(0.02, 0.1)) * gain * os.triangle(freq);

wet_sound = 0.04*dry_sound : re.mono_freeverb(0.5, 0.5, 0.5, 0.5);

process = (wet_sound + (dry_sound*0.96))<:_,_;
