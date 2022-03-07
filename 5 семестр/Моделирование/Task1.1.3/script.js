var arg = Math.PI / 15;

function setup() {
    createCanvas(1000, 1000);
}

function draw() {
    background(255, 255, 255);
    let scale = Number(document.getElementById("scale").value);
    let ang = Number(document.getElementById("ang").value) * 1/Math.PI;
    drawTree(600, 600, ang, scale, 100);
}

function drawTree(px, py, ang, scale, len) {
    // вводим случайный угол смещения для изменения формы
     var rn = (Math.PI / 180);
     if (document.getElementById("randAngle").checked)
      rn *= Math.random() * 10;
     else
      rn *= Number(document.getElementById("angle").value);
  
     var x = Math.floor(scale*len*Math.cos(ang));
     var y = Math.floor(scale*len*Math.sin(ang));
    

     line(px, py, px + x, py - y);
  
     if (scale*len<20)
            return;
  
      drawTree(px + x, py - y, ang - arg + rn, scale, scale*len);
      drawTree(px + x, py - y, ang + arg + rn, scale, scale*len);
  }