declare name "Gong";
declare nvoices "16";

import("stdfaust.lib");

freq = nentry("freq", 440, 20, 20000, 0.01);
gain = nentry("gain", 0.3, 0, 10, 0.01) : si.smoo;
gate = button("gate");
initgain = nentry("initgain", 0.3, 0, 10, 0.01) : si.smoo;

RightHorizontal = hslider("righthorizontal", 0.5, 0, 1, 0.01);
RightVertical = hslider("rightvertical", 0.5, 0, 1, 0.01);

Envelope = initgain * en.adsr(0.1, 0.2, 0.5, 2);

mod_ind = 2 * RightHorizontal;
mod = os.osc(freq * (3/2));
car = os.osc(freq + freq*mod_ind*mod);

dry_sound = gain * (gate : Envelope) * car : fi.lowpass(2, 20000);

wet_sound = 0.05*dry_sound : re.mono_freeverb(0.5, 0.5, 0.5, 0.5);

process = wet_sound + (dry_sound*0.95)<:_,_;
