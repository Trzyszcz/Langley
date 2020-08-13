declare name "normik3";
declare nvoices "16";

import("stdfaust.lib");

freq = nentry("freq", 440, 20, 20000, 0.01);
gain = nentry("gain", 0.3, 0, 10, 0.01);
gate = button("gate");

RightHorizontal = hslider("righthorizontal", 0.5, 0, 1, 0.01);
RightVertical = hslider("rightvertical", 0.5, 0, 1, 0.01);


frequency = freq * (1 + ( (os.osc(7) * 3 * RightHorizontal)/100) );
timbre = os.osc(frequency) + (os.osc(2*frequency) * RightVertical);

process = gain * gate * timbre;
