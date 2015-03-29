module.exports = function (grunt) {
    require('load-grunt-tasks')(grunt);

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        setting: {
            webapp: 'src/main/webapp',
            tsMain: 'src/main/webapp/assets/grunt/ts',
            tsTest: 'src/main/webapp/assets/grunt/ts',
            typings: 'src/main/webapp/assets/grunt/ts/typings',
            dist: 'dist'
        },
        clean: {
            all: {
                src: [
                    '<%= setting.typings %>/*'
                ]
            },
        },
        typescript: {
            base: {
                src: ['<%= setting.webapp %>/assets/grunt/ts/**/*.ts'],
                dest: '<%= setting.webapp %>/assets/js/ts-all.js',
                options: {
                    module: 'amd',
                    target: 'es5',
                    sourceMap: true
                }
            }
        },
        sass: {
            dist: {
                options: {
                    sourcemap: true
                },
                files: {
                    'src/main/webapp/assets/css/application-scss.css': ['<%= setting.webapp %>/assets/grunt/scss/application.scss']
                }
            }
        },
        concat: {
            options: {
                separator: ';',
            },
            dist: {
                src: [
                    '<%= setting.webapp %>/assets/js/ts-all.js'
                ],
                dest: '<%= setting.webapp %>/assets/js/application-all.js',
            },
        },
        watch: {
            files: '<%= setting.webapp %>/assets/grunt/**/*',
            tasks: ['compile']
        },
        mochaTest: {
            test: {
                options: {
                    reporter: 'spec'
                },
                src: ['test/**/*.js']
            }
        },
        mocha_phantomjs: {
            all: ['src/test/phantomjs/index.html']
        },
        uglify: {
            my_target: {
                files: {
                    'src/main/webapp/assets/js/application-all.min.js': ['<%= setting.webapp %>/assets/js/application-all.js']
                }
            }
        },
        'wiredep': {
            task: {
                src: [
                    '<%= setting.webapp %>/index.html'
                ],
                ignorePath: '<%= setting.webapp %>',
                exclude: ['bootstrap-sass'],
                fileTypes: {
                    html: {
                        replace: {
                            js: '<script src="{{filePath}}"></script>',
                            css: '<link rel="stylesheet" href="/{{filePath}}" />'
                        }
                    }
                }
            }
        },
        tsd: {
            client: {
                options: {
                    // execute a command
                    command: 'reinstall',

                    //optional: always get from HEAD
                    latest: false,

                    // optional: specify config file
                    config: './tsd.json'
                }
            }
        },
        tslint: {
            options: {
                formatter: "prose",
                configuration: grunt.file.readJSON("tslint.json")
            },
            files: {
                src: [
                    '<%= setting.tsMain %>/controller/**/*.ts',
                    '<%= setting.tsMain %>/filter/**/*.ts',
                    '<%= setting.tsMain %>/service/**/*.ts',
                    '<%= setting.tsMain %>/*.ts'
                ]
            }
        }
    });

    grunt.registerTask('setup', ['clean', 'tsd']);
    grunt.registerTask('compile', ['wiredep', 'typescript', 'tslint', 'sass', 'concat']);
    grunt.registerTask('phantomJsTest', ['connect', 'mocha_phantomjs']);
    grunt.registerTask('test', ['compile', 'mochaTest', 'phantomJsTest']);
    grunt.registerTask('build', ['compile', 'mochaTest', 'uglify']);
    grunt.registerTask('default', ['compile', 'uglify', 'watch']);

}

