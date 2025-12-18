const soundCache = {};
function loadSound(name) {
  const audio = new Audio(`sounds/${name}.mp3`);
  soundCache[name] = audio;
}
['correct', 'wrong', 'click'].forEach(loadSound);

function playSound(name) {
  if (!soundsEnabled || !soundCache[name]) return;
  const audio = soundCache[name].cloneNode(); // avoids overlap cutoffs
  audio.play().catch(() => {});
}
