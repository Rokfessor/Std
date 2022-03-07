let x1 = 0, x2 = 800;
function setup() {
    createCanvas(1000, 1000);
}

function draw() {
    x1++;
    x2--;
    background(255, 255, 255);

    let count = document.getElementById("count").value;
    let size = document.getElementById("size").value;
    
    for (let i = 0; i < count; i++) {
        fill(0, 0, 0, 0);
        circle(x1, 300, i * size);
        circle(x2, 300, i * size);
    }
}