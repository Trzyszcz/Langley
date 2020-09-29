declare name "Mou";
declare nvoices "16";

import("stdfaust.lib");

freq = nentry("freq", 440, 20, 20000, 0.01);
gain = nentry("gain", 0.3, 0, 10, 0.01);
gate = button("gate");

RightHorizontal = hslider("righthorizontal", 0.5, 0, 1, 0.01);
RightVertical = hslider("rightvertical", 0.5, 0, 1, 0.01);

Envelope = en.adsr(0.1, 0, 1, 0.1);

freque1 = freq + (freq*0.007*no.lfnoise0(32)*RightVertical*3);
freque2 = freq + (freq*0.014*no.lfnoise0(32)*RightVertical*3);

basictimbre(x) = (1/2) * os.triangle(x) + (1/3) * os.triangle(3*x);

is_bet(x, y, z) = (x >= y) & (x < z); //is x between y and z

distortion(x) = 
	(x < -0.08905) * ( -1/4 * (1 - ( (1 - abs(x) - 0.032847) ^ 12) ) + 0.01) +
	is_bet(x, -0.08905, 0.320018) * ( (-6.153*(x^2)) + (3.9375*x) ) +
	(0.320018 < x) * 0.630035;

dry_sound = distortion(gain * (gate : Envelope) * (basictimbre(freque1) + basictimbre(freque2)) ) : fi.lowpass(2, 9000);

wet_sound = 0.05*dry_sound : re.mono_freeverb(0.5, 0.5, 0.5, 0.5);

process = wet_sound + (dry_sound*0.95)<:_,_;
