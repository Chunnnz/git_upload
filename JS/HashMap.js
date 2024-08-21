
let HashMap = function () {
    let map = {};

    return {
        put: function (key, value) {
            map[key] = value;
        },

        keys: function () {
            let arr = [];
            for (let key in map) {
                arr.push(key);
            }
            return arr;
        },

        contains: function (key) {
            return key in map;
        },

        get: function (key) {
            map[key]
            return map[key];
        },

        clear: function () {
            map = {};
        }

    };
};