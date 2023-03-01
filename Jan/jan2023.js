const fs = require("fs");
// const input = fs.readFileSync("dev/stdin").toString().split(" ");
const input = fs
  .readFileSync("input.txt")
  .toString()
  .split(/[ ]|\r\n/);
console.log(input);
const pointCount = parseInt(input[0]);
let points = new Array();
for (let i = 0; i < pointCount; i++) {
  points.push({
    x: parseInt(input[i * 2 + 1]),
    y: parseInt(input[i * 2 + 2]),
  });
}

// 1. x1 - x2가 큰 순으로 정렬
points = points.sort((a, b) => {
  const x = a.x - b.x;
  if (x == 0) {
    return a.y - b.y;
  }
  return x;
  // return b.x - a.x > 0 && b.y - a.y > 0;
});

console.log(points);
// 2. 각 x1 - x2들에 대해 y1 - y2 순으로 정렬

// 3. 제일 큰 놈끼리만 비교해서 출력
