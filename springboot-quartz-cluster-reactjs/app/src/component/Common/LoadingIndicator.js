import React from 'react';

export default function LoadingIndicator() {
    return (
        <div className="spinner-border" role="status">
            <span className="sr-only">Loading...</span>
        </div>
    );
}