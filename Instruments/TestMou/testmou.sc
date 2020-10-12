FaustTestMou : MultiOutUGen
{
  *ar { | freq(440.0), gain(0.3), gate(0.0), rightvertical(0.5) |
      ^this.multiNew('audio', freq, gain, gate, rightvertical)
  }

  *kr { | freq(440.0), gain(0.3), gate(0.0), rightvertical(0.5) |
      ^this.multiNew('control', freq, gain, gate, rightvertical)
  } 

  init { | ... theInputs |
      inputs = theInputs
      ^this.initOutputs(2, rate)
  }

  name { ^"FaustTestMou" }


  info { ^"Generated with Faust" }
}

