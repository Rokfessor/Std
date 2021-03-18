function calculate(){
	console.log(LongInt.sum(document.getElementById("firstNumber").value, document.getElementById("secondNumber").value))
}

class LongInt{
	constructor(name){
		this.name = name;
	}

	static sum(a, b){
		var negative = 1
		
		if (a < 0 && b < 0){
			a *= -1
			b *= -1
			negative = -1;
		}

		var max = a, min = b;

		if (max < min){
			[max, min] = [min, max]; //swap
		}

		var maxInd = max.length - 1, minInd = min.length - 1, ost = 0;
		var res = ""

		for (let i = 0; i < max.length; i++){
			var a = parseInt(max[maxInd])

			if (minInd !== -1){
				var b = parseInt(min[minInd])
				var c = a + b + ost
				ost = 0;

				if (c >= 10) {
					c -= 10;
					ost++
				}
				res += c
				minInd--;
			} else{
				var c = a + ost
				ost = 0
				if (c >= 10){
					c -= 10
					ost++
				}
				res += c
			}
			maxInd--;
		}

		console.log(res.split("").reverse().join(""))

		return parseInt(res.split("").reverse().join("")) * negative; //разворачиваем строку
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