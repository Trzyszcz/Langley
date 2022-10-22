FaustGong : MultiOutUGen
{
  *ar { | freq(110.0), gain(0.3), gate(0.0), mod_ind(1.1) |
      ^this.multiNew('audio', freq, gain, gate, mod_ind)
  }

  *kr { | freq(110.0), gain(0.3), gate(0.0), mod_ind(1.1) |
      ^this.multiNew('control', freq, gain, gate, mod_ind)
  } 

  init { | ... theInputs |
      inputs = theInputs
      ^this.initOutputs(2, rate)
  }

  name { ^"FaustGong" }


  info { ^"Generated with Faust" }
}

