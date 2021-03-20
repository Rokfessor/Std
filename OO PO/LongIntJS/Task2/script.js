function calculate(){
	console.log(LongInt.sum(document.getElementById("firstNumber").value, document.getElementById("secondNumber").value))
}

class LongInt{
	constructor(name){
		this.name = name;
	}

	static sum(a, b){
		var negative = 1
		if (a[0] == '-' && b[0] == '-'){
			a = a.substring(1, a.length - 2)
			b = b.substring(1, b.length - 2)
			negative = -1;
		}

		console.log(a + " " + b)

		var max = a, min = b;

		if (max.toString().length < min.toString().length){
			[max, min] = [min, max]; //swap
		}

		var maxInd = max.toString().length - 1, minInd = min.toString().length - 1, ost = 0;
		var res = ""

		for (let i = 0; i < max.toString().length; i++){
			var n1 = parseInt(max[maxInd])

			if (minInd !== -1){
				var n2 = parseInt(min[minInd])
				console.log(maxInd + " " + minInd)
				var c = n1 + n2 + ost
				ost = 0;

				if (c >= 10) {
					c -= 10;
					ost++
				}
				res += c
				minInd--;
			} else{
				var c = n1 + ost
				ost = 0
				if (c >= 10){
					c -= 10
					ost++
				}
				res += c
			}
			maxInd--;
		}

		res += ost

		console.log(res)

		return parseInt(res.split("").reverse().join("")) * negative; //разворачиваем строку и возвращаем число
	}

	static abs(a){
		if (a < 0)
			return a * -1;
		return a;
	}

	static diff(a, b){
		console.log("diff");
	}

	static mult(a, b){
		console.log(a.name);
		console.log(b.name);
	}

	static div(a, b){
		console.log(a.name);
		console.log(b.name);
	}

	static mod(a, b){
		console.log(a.name);
		console.log(b.name);
	}

	static equals(a, b){
		console.log(a.name);
		console.log(b.name);
	}

}