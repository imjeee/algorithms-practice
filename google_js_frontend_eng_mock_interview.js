// Online Javascript Editor for free
// Write, Edit and Run your Javascript code using JS Online Compiler

console.log('Welcome to Programiz!')

function promiseAll(promises) {
	// map each promise to array, note position
	// create count to keep track of how many promises are resolved
	// if all are resolved, return result array
	let settledPromisesCounter = 0
	const output = []

	return new Promise((resolve, reject) => {
		promises.forEach((promise, index) => {
			promise
				.then((value) => {
					output[index] = value
					settledPromisesCounter++
					if (settledPromisesCounter === promises.length) {
						resolve(output)
					}
				}, console.log)
				.catch(reject)
		})
	})
}

promiseAll([
	new Promise((resolve, reject) =>
		setTimeout(() => resolve(0), Math.random() * 100)
	),
	new Promise((resolve, reject) =>
		setTimeout(() => resolve(1), Math.random() * 100)
	),
	new Promise((resolve, reject) =>
		setTimeout(() => resolve(2), Math.random() * 100)
	),
	new Promise((resolve, reject) =>
		setTimeout(() => resolve(3), Math.random() * 100)
	),
	new Promise((resolve, reject) =>
		setTimeout(() => resolve(4), Math.random() * 100)
	),
	Promise.reject('error')
]).then(console.log)

function deepEquals(valueOne, valueTwo) {
	if (valueOne === valueTwo) return true

	if (typeof valueOne !== typeof valueTwo) return false

	if (typeof valueOne !== 'object') {
		// if (isNaN(valueOne) && isNaN(valueTwo)) return false
		return false
	}

	if (Array.isArray(valueOne)) {
		if (valueOne.length !== valueTwo.length) return false
		for (let i = 0; i < valueOne.length; i++) {
			if (!deepEquals(valueOne[i], valueTwo[i])) return false
		}
		return true
	}

	if (!deepEquals(Object.entries(valueOne), Object.entries(valueTwo)))
		return false

	return true
}

function updateTimer(isoDate, timerInfo) {
	// hours, minutes, seconds: number
	const date = new Date(isoDate)
	const timeTill = date - Date.now()
	// 1 sec = 1000 ms
	// 1 min = 60s
	// 1 hr = 60m
	// 1 day = 24hr

	const seconds = Math.floor(timeTill / 10000)
	const minutes = Math.floor(seconds / 60)
	const hours = Math.floor(hours / 60)

	setInterval(() => {
		timerInfo.seconds = seconds % 60
		;(timerInfo.minutes = minutes % 60), (timerInfo.hours = hours)
	}, 500)
}
