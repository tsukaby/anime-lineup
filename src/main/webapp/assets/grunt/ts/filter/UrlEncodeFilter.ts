module Filter {
    "use strict";

    export function UrlEncodeFilter() {
        return (input:string):string => {
            return encodeURIComponent(input);
        };
    }
}
