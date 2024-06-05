class SongNode {
    String data;
    SongNode next;

    public SongNode(String data) {
        this.data = data;
        this.next = null;
    }
}

class SongStack {
    private SongNode top;

    public SongStack() {
        this.top = null;
    }

    public void push(String data) {
        SongNode newNode = new SongNode(data);
        newNode.next = top;
        top = newNode;
    }

    public String pop() {
        if (isEmpty()) {
            return null;
        }
        String poppedData = top.data;
        top = top.next;
        return poppedData;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void display() {
        SongNode current = top;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}
