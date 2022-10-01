declare name "Vang";
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

noise_for_unison(y) = no.multinoise(4) :
     ba.downSample(y), ba.downSample(y), ba.downSample(y),ba.downSample(y) :
     fi.lowpass(1, y),fi.lowpass(1, y), fi.lowpass(1, y),fi.lowpass(1, y);

add_noise_to_freq(base_freq, noise) = base_freq*(1 + (noise*0.014*LeftVertical));

noised_frequencies(base_freq) = noise_for_unison(32) :
     add_noise_to_freq(base_freq), add_noise_to_freq(base_freq), add_noise_to_freq(base_freq), add_noise_to_freq(base_freq);

timbre(x) = noised_frequencies(x) :
    os.sawtooth, os.sawtooth, os.sawtooth, os.sawtooth :> fi.lowpass(1, 100 + (400*RightVertical));

dry_sound = timbre(freq)*(gate : Envelope)*gain;

wet_sound = 0.15*dry_sound : re.mono_freeverb(0.5, 0.5, 0.5, 0.5);

main_stream = wet_sound + (dry_sound*0.85);

long_delay = + ~ ( @(5*48000) *(19/20) );
short_delay = + ~ ( @(0.5*48000) *(1/3) );

short_delayed_stream = main_stream * RightPush : short_delay;
long_delayed_stream = ( main_stream + short_delayed_stream ) * LeftPush : long_delay;

process = main_stream + short_delayed_stream + long_delayed_stream <:_,_;
