module Filter {
    "use strict";

    export function SeasonColorFilter() {
        return (input:number):string => {
            if (input === 1) {
                return "#5141D9";
            } else if (input === 2) {
                return "#B2F63D";
            } else if (input === 3) {
                return "#FF4540";
            } else if (input === 4) {
                return "#FFAC40";
            } else {
                return "#000000";
            }
        };
    }
}
