const path = require('path');
const argv = require('yargs').argv;

const CopyWebpackPlugin = require('copy-webpack-plugin');

const productionBuild = argv.p || false;
const outputDir = path.resolve('../backend/build/classes/main/static');

module.exports = function() {
    const version = '0.0.1';
    console.log('### frontend version ' + version + '\r');

    const webpackConfig = {
        mode: productionBuild ? 'production' : 'development',
        entry: { 'app': './src/js/main.tsx'},
        output: {
            path: outputDir,
            publicPath: '/',
            filename: './js/[name].js',
            chunkFilename: './js/[name].bundle.js'
        },
        resolve: {
            extensions: [".ts", ".tsx", ".js", ".jsx"],
            modules: [
                path.join(__dirname, '../backend/src/main/resources'),
                path.join(__dirname, './src/js'),
                'src/js/components',
                'node_modules',
            ],
        },
        module: {
            rules: [
                {
                    test: /\.tsx?$/,
                    exclude: /(node_modules)/,
                    use: [
                        { loader: 'ts-loader' },
                    ],
                },
                {
                    enforce: "pre",
                    test: /\.js$/,
                    loader: "source-map-loader"
                }
            ],
        },
        plugins: [
            new CopyWebpackPlugin([
                {
                    context: 'src/html',
                    from: '**/*',
                    to: '../templates',
                },
                {
                    context: 'src/js',
                    from: '*',
                    to: 'js',
                    ignore: ['main.tsx'],
                },
                {
                    context: 'src/css',
                    from: '**/*',
                    to: 'css',
                },
                {
                    context: 'src/img',
                    from: '**/*',
                    to: 'img',
                },
            ]),
        ],
    };

    if (!productionBuild) {
        console.log('### sourcemap is enabled.\r');
        webpackConfig.devtool = "#inline-source-map";
    } else {
        console.log('### sourcemap is disabled.\r');
    }

    return webpackConfig;
};
