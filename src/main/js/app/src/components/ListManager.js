import React, { Component } from 'react';
import '../App.css';

class ListManager extends Component {

    state = {
        selectedList: {},
        selectedTask: {},
        isLoading: true,
        lists: [],
        tasks: [],
        newListData: {
            name: ''
        },
        newTaskData: {
            name: '',
            description: ''
        }
    };

    componentDidMount() {
        this.setState({isLoading: true});

        fetch('api/v1/list/')
            .then(response => response.json())
            .then(lists => {
                if (lists.length > 0) {
                    fetch('api/v1/list/' + lists[0].id)
                        .then(response => response.json())
                        .then(data => {
                            this.setState(
                                {lists: lists, tasks: data.tasks, isLoading: false, selectedList: lists[0].id})
                        })
                } else {
                    this.setState({lists: lists, isLoading: false})
                }
        })
    }


    chooseTask(id) {
        const {tasks} = this.state
        this.setState({selectedTask: tasks.find( v => v.id === id)})
    }

    deleteTask() {
        const {tasks, selectedTask} = this.state

        if (selectedTask.id && selectedTask.id !== 0) {
            fetch('api/v1/task/' + selectedTask.id, {method: 'DELETE'})
                .then( response => {
                    if (response.status === 200) {
                        this.setState({
                            selectedTaskId: 0,
                            tasks: tasks.filter( v => v.id !== selectedTask.id)
                        })
                    }
                })
        }
    }

    selectList(id) {
        const {lists} = this.state
        const selectedListId = Number.parseInt(id)

        fetch('api/v1/list/' + id, {method: 'GET'})
            .then( response => {
                if (response.status === 200) {
                    response.json().then(data => {
                        this.setState({
                            selectedList: lists.find( v => v.id === selectedListId),
                            tasks: data.tasks
                        })
                    })
                }
            })
    }

    createList() {
        const {newListData, lists, selectedList} = this.state

        if (newListData.name.length > 0) {
            fetch('api/v1/list/', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(newListData)
            })
                .then(response => {
                    if (response.status === 200) {
                        response.json().then( data => {
                            this.setState({
                                selectedList: !selectedList.id ? data : selectedList,
                                newListData: {name: ''},
                                lists: lists.concat([data])
                            })
                        })
                    }
                })
        }
    }

    changeTaskState() {
        const {selectedTask, tasks} = this.state
        selectedTask.completed = !selectedTask.completed;

        if (selectedTask) {
                fetch('api/v1/task/', {
                    method: 'POST',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify(selectedTask)
                })
                    .then(response => {
                        if (response.status === 200) {
                            response.json().then( data => {
                                const changedTask = tasks.find(v => v.id === selectedTask.id)
                                changedTask.completed = selectedTask.completed
                                changedTask.version = data.version
                                this.setState({tasks: tasks})
                            })
                        }
                    })
            }
    }

    createTask() {
        const {newTaskData, tasks, selectedList} = this.state

        if (newTaskData.name.length > 0 && selectedList.id) {
            newTaskData.listId = selectedList.id
            fetch('api/v1/task/', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(newTaskData)
            })
                .then(response => {
                    if (response.status === 200) {
                        response.json().then( data => {
                            this.setState({
                                newTaskData: {name: '', description: ''},
                                tasks: tasks.concat([data])
                            })
                        })
                    }
                })
        }

    }

    render() {
        const {lists, isLoading, tasks, selectedTask, selectedList} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        return (
            <div>
                <h2>List of tasks:</h2>
                <select onChange={event => this.selectList.call(this, event.target.value)}>
                    {lists.map(list =>
                        <option value={list.id}>
                            {list.name}
                        </option>
                    )}
                </select>
                <div>
                    <ul>
                        {tasks.map(task =>
                            <li className={task.id === selectedTask.id ? 'selectedTask' : ''}
                                onClick={this.chooseTask.bind(this, task.id)}>
                                {task.name} -- {task.description} -- {task.completed ? 'выполнено' : 'не выполнено' }
                            </li>
                        )}
                    </ul>
                </div>
                <button onClick={this.deleteTask.bind(this)}>delete task</button>
                <button onClick={this.changeTaskState.bind(this)}> change task state</button>

                <div>
                    <h2>Create new list</h2>
                    <div>
                        <label>List name:</label><input onChange={
                            (event) => this.setState({newListData : {name: event.target.value}}) } type="text"/>
                        <button onClick={this.createList.bind(this)}>create</button>
                    </div>
                </div>

                <div>
                    <h2>Add task to list</h2>
                    <div>
                        <label>Task name:</label> <input  type="text" onChange={
                        (event) => this.setState({newTaskData : {name: event.target.value, description: this.state.newTaskData.description}}) }/>
                    </div>
                    <div>
                        <label>Task description:</label> <input  type="text" onChange={
                        (event) => {this.setState({newTaskData : {description: event.target.value, name: this.state.newTaskData.name}})} }/>
                    </div>
                    <button onClick={this.createTask.bind(this)}>add</button>
                </div>
            </div>
        );
    }
}

export default ListManager;