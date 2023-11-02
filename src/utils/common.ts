function debounce(fn, delay = 1000) {
    let timer
    return function () {
        window.clearTimeout(timer)
        timer = setTimeout(() => {
            fn.apply(this, arguments)
            window.clearTimeout(timer)
        }, delay)
    }
}

function getFakeUrl() {
    return [{
        url: 's'
    }, {
        url: 's'
    }, {
        url: 's'
    }, {
        url: 's'
    }, {
        url: 's'
    }, {
        url: 's'
    }, {
        url: 's'
    }, {
        url: 's'
    }, {
        url: 's'
    }, {
        url: 's'
    }, {
        url: 's'
    },]
}

export {
    getFakeUrl
}

