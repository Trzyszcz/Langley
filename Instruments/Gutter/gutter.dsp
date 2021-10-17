declare name "gutter";
declare nvoices "16";

import("stdfaust.lib");

freq = nentry("freq", 440, 20, 20000, 0.01);
gain = nentry("gain", 0.3, 0, 10, 0.01) : si.smoo;
gate = button("gate");

LeftVertical = hslider("leftvertical", 0.5, 0, 1, 0.01);
RightVertical = hslider("rightvertical", 0.5, 0, 1, 0.01);

Envelope = en.adsr(0.1, 0, 1, 0.1);

basictimbre(x) = (1/2)*os.pulsetrain(x*1.01, LeftVertical/2) + (1/2)*os.pulsetrain(x*0.99, LeftVertical/2) : fi.resonlp( (8*RightVertical + 4)*x, 1, 1);

dry_sound = gain * (gate : Envelope) * basictimbre(freq);

wet_sound = 0.05*dry_sound : re.mono_freeverb(0.5, 0.5, 0.5, 0.5);

process = wet_sound + (0.95 * dry_sound) <: _,_;

//some old stuff, sorry for keeping it
/*
basictimbre(x) = (os.sawtooth(x) + (1/2)*os.sawtooth(2*x) + (1/3)*os.sawtooth(3*x)) : fi.lowpass(1, (8*RightHorizontal + 4)*x);

is_bet(x, y, z) = (x >= y) & (x < z); //is x between y and z

distortion(x) = 
	(x < -0.08905) * ( -1/4 * (1 - ( (1 - abs(x) - 0.032847) ^ 12) ) + 0.01) +
	is_bet(x, -0.08905, 0.320018) * ( (-6.153*(x^2)) + (3.9375*x) ) +
	(0.320018 < x) * 0.630035;

dry_sound = distortion(gain * (gate : Envelope) * basictimbre(freq)) : fi.lowpass(1, (8*RightVertical + 4)*freq);

wet_sound = 0.05*dry_sound : re.mono_freeverb(0.5, 0.5, 0.5, 0.5);

process = wet_sound + (dry_sound*0.95)<: co.compressor_stereo(1.1, 0, 1, 0.5);
*/
