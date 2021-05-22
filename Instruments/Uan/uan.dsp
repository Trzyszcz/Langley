declare name "Uan";
declare nvoices "16";

import("stdfaust.lib");

freq = nentry("freq", 440, 20, 20000, 0.01);
gain = nentry("gain", 0.3, 0, 10, 0.01) : si.smoo;
gate = button("gate");

RightHorizontal = hslider("righthorizontal", 0.5, 0, 1, 0.01);
RightVertical = hslider("rightvertical", 0.5, 0, 1, 0.01);

Envelope = en.adsr(0.1, 0, 1, 0.1);

freque(frey) = frey + (frey*RightHorizontal*os.triangle(16*frey));

osc_base(frex) = gain * (gate : Envelope) * (os.square(frex)) : fi.lowpass(2, 500);

freqD = freq * (1 - (RightVertical/100));
freqU = freq * (1 + (RightVertical/100));

dry_sound = ((2/3) * osc_base(freque(freq))) + ((1/4) * osc_base(freque(freqD))) + ((1/4) * osc_base(freque(freqU)));

dry_soundEQ = dry_sound + ( (1/4) * dry_sound : fi.lowpass(2,100) ) + ( (1/4) * dry_sound : fi.lowpass(2, 50) );

wet_sound = 0.15*dry_soundEQ : re.mono_freeverb(0.5, 0.5, 0.5, 0.5);

process = wet_sound + (dry_sound*0.85)<:_,_;
