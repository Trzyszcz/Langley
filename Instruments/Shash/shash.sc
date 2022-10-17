FaustShash : MultiOutUGen
{
  *ar { | leftpush(0.0), rightpush(0.0), freq(440.0), gain(0.3), gate(0.0), rightvertical(0.5) |
      ^this.multiNew('audio', leftpush, rightpush, freq, gain, gate, rightvertical)
  }

  *kr { | leftpush(0.0), rightpush(0.0), freq(440.0), gain(0.3), gate(0.0), rightvertical(0.5) |
      ^this.multiNew('control', leftpush, rightpush, freq, gain, gate, rightvertical)
  } 

  init { | ... theInputs |
      inputs = theInputs
      ^this.initOutputs(2, rate)
  }

  name { ^"FaustShash" }


  info { ^"Generated with Faust" }
}

