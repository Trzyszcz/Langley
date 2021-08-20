declare name "bassdrum";
declare nvoices "16";

import("stdfaust.lib");

freq = nentry("frequency", 440, 20, 20000, 0.001);
gain = nentry("gain", 0.3, 0, 1, 0.001);
gate = button("gate");

attack = nentry("attack", 0.02, 0, 2, 0.001);
decay = nentry("decay", 0.1, 0.001, 3, 0.001);
freq_decay = nentry("freq_decay", 0.1, 0, 3, 0.001);
freq_slide= nentry("freq_slide", 0, 0, 2, 0.001);

dry_sound = (gate : en.ar(attack, decay)) * gain * os.triangle(freq + (freq*freq_slide* (gate:en.ar(0, freq_decay)) ) );

wet_sound = 0.04*dry_sound : re.mono_freeverb(0.5, 0.5, 0.5, 0.5);

process = (wet_sound + (dry_sound*0.96))<:_,_;
