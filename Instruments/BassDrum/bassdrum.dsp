declare name "bassdrum";
declare nvoices "16";

import("stdfaust.lib");

freq = nentry("frequency", 40, 20, 20000, 0.001);
gain = nentry("gain", 1, 0, 1, 0.001);
gate = button("gate");

attack = nentry("attack", 0.001, 0, 2, 0.001);
decay = nentry("decay", 0.1, 0.001, 3, 0.001);
freq_decay = nentry("freq_decay", 0.1, 0, 3, 0.001);
freq_slide= nentry("freq_slide", 0, 0, 2, 0.001);

//dry_sound = (gate : en.ar(attack, decay)) * gain * (no.noise : fi.resonlp(freq + (freq*freq_slide* (gate:en.ar(0, freq_decay)) ), 5, 1));

dry_sound = (gate : en.ar(attack, decay)) * gain * (no.noise : fi.resonlp(80, 20, 3));

wet_sound = 0.04*dry_sound : re.mono_freeverb(0.5, 0.5, 0.5, 0.5);

process = (wet_sound + (dry_sound*0.96))<:_,_;
