package ibf2022.paf.assessment.server.services;

import ibf2022.paf.assessment.server.models.Task;

public class InsertException extends Exception{

    private Task taskInfo;

    public InsertException(){
        super();
    }

    public InsertException(String msg){
        super(msg);
    }

    public Task getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(Task taskInfo) {
        this.taskInfo = taskInfo;
    }

    

    
}
