import {applyMiddleware, compose, createStore} from 'redux';
import modules from './modules';
import ReduxThunk from 'redux-thunk';


const configure = () => {
    // 개발 모드일 때만 Redux Devtools 적용
    const isDev = process.env.NODE_ENV === 'development';
    const devtools = isDev && window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__;
    const composeEnhancers = devtools || compose;

    const middlewares = [ReduxThunk];

    const store = createStore(modules, composeEnhancers(
        applyMiddleware(...middlewares)
    ));
    return store;
}

export default configure;