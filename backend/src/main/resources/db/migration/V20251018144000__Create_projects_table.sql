CREATE TABLE IF NOT EXISTS projects (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    start_date DATE,
    end_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create index on name for better query performance
CREATE INDEX idx_projects_name ON projects(name);

-- Create index on start_date for date range queries
CREATE INDEX idx_projects_start_date ON projects(start_date);

-- Add comment to table
COMMENT ON TABLE projects IS 'Stores project information including name, description, and date ranges';
