module Filter {
    "use strict";

    export function SeasonNameFilter() {
        return (input:number):string => {
            if (input === 1) {
                return "冬";
            } else if (input === 2) {
                return "春";
            } else if (input === 3) {
                return "夏";
            } else if (input === 4) {
                return "秋";
            } else {
                return "";
            }
        };
    }
}
