const path = require('path');
const argv = require('yargs').argv;

const CopyWebpackPlugin = require('copy-webpack-plugin');

const productionBuild = argv.p || false;
const outputDir = path.resolve('../backend/build/resources/main/static');

module.exports = function() {
    const version = '0.0.1';
    console.log('### frontend version ' + version + '\r');

    const webpackConfig = {
        mode: productionBuild ? 'production' : 'development',
        entry: { 'app': './src/js/main.js'},
        output: {
            path: outputDir,
            publicPath: '/',
            filename: './js/[name].js',
            chunkFilename: './js/[name].bundle.js'
        },
        resolve: {
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
                    test: /\.jsx?$/,
                    exclude: /(node_modules)/,
                    use: [
                        { loader: 'babel-loader', options: { presets: ['@babel/preset-react'] } },
                    ],
                },
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
