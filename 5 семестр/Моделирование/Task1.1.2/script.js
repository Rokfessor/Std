let x0, y0;
let r1 = 180, r2 = 60;

function setup() {
    createCanvas(1000, 1000);
}

function draw() {
    background(255, 255, 255);
    x0 = 500;
    y0 = 500;
    drawStar();
}

function calcUgol(a1, a2, a3) {
    let x1 = Math.round(x0 + r1 * Math.cos(a1)), y1 = Math.round(y0 - r1 * Math.sin(a1));
    let x2 = Math.round(x0 + r2 * Math.cos(a2)), y2 = Math.round(y0 - r2 * Math.sin(a2));
    let x3 = Math.round(x0 + r2 * Math.cos(a3)), y3 = Math.round(y0 - r2 * Math.sin(a3));
    color(0, 0, 0);
    stroke(51);
    strokeWeight(4);
    line(x1, y1, x2, y2);
    line(x1, y1, x3, y3);
}

function drawStar() {
    r1 = Number(document.getElementById("outRad").value);
    r2 = Number(document.getElementById("inRad").value);
    let a1 = 0;
    let n = Number(document.getElementById("count").value);
    for (let i = 1; i <= n; i++) {
        a1 = a1 + 2 * Math.PI / n;
        let a2 = a1 + Math.PI / n;
        let a3 = a1 - Math.PI / n;
        calcUgol(a1, a2, a3);
    }
}