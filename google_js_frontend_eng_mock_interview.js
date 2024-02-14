catch(reject)
})
    })
}

promiseAll([
    new Promise((resolve, reject) => setTimeout(() => resolve(0), Math.random() * 100)),
    new Promise((resolve, reject) => setTimeout(() => resolve(1), Math.random() * 100)),
    new Promise((resolve, reject) => setTimeout(() => resolve(2), Math.random() * 100)),
    new Promise((resolve, reject) => setTimeout(() => resolve(3), Math.random() * 100)),
    new Promise((resolve, reject) => setTimeout(() => resolve(4), Math.random() * 100)),
    Promise.reject('error')
]).then(console.log)

function deepEquals(valueOne, valueTwo) {
    if (valueOne === valueTwo)
	return true

    if (typeof valueOne !== typeof valueTwo)
	return false

    if (typeof valueOne !== 'object') {
	// if (isNaN(valueOne) && isNaN(valueTwo)) return false
	return false
    }

    if (Array.isArray(valueOne)) {
	if (valueOne.length !== valueTwo.length)
	    return false
	for (let i = 0; i < valueOne.length; i++) {
	    if (!deepEquals(valueOne[i], valueTwo[i]))
		return false
	}
	return true
    }

    if (!deepEquals(Object.entries(valueOne), Object.entries(valueTwo)))
	return false

    return true
}
