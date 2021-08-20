FaustBassdrum : MultiOutUGen
{
  *ar { | attack(0.02), decay(0.1), freq_decay(0.1), freq_slide(0.0), frequency(440.0), gain(0.3), gate(0.0) |
      ^this.multiNew('audio', attack, decay, freq_decay, freq_slide, frequency, gain, gate)
  }

  *kr { | attack(0.02), decay(0.1), freq_decay(0.1), freq_slide(0.0), frequency(440.0), gain(0.3), gate(0.0) |
      ^this.multiNew('control', attack, decay, freq_decay, freq_slide, frequency, gain, gate)
  } 

  init { | ... theInputs |
      inputs = theInputs
      ^this.initOutputs(2, rate)
  }

  name { ^"FaustBassdrum" }


  info { ^"Generated with Faust" }
}

