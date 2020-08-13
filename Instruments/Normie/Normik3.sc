FaustNormik3 : UGen
{
  *ar { | freq(440.0), gain(0.3), gate(0.0), righthorizontal(0.5), rightvertical(0.5) |
      ^this.multiNew('audio', freq, gain, gate, righthorizontal, rightvertical)
  }

  *kr { | freq(440.0), gain(0.3), gate(0.0), righthorizontal(0.5), rightvertical(0.5) |
      ^this.multiNew('control', freq, gain, gate, righthorizontal, rightvertical)
  } 

  name { ^"FaustNormik3" }


  info { ^"Generated with Faust" }
}

