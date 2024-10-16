<template>
  <div class="chat-container">
    <div class="chat-messages">
      <div v-for="message in messages" :key="message.id" class="message">
        <div class="message-content">{{ message.content }}</div>
        <div class="message-author">{{ message.author }}</div>
      </div>
    </div>
  </div>
  <div class="input-container">
    <form @submit.prevent="sendMessage">
      <input v-model="newMessage" type="text" placeholder="您想问什么?"/>
      <button type="submit">Send</button>
    </form>
  </div>

</template>

<script>
import { fetchEventSource } from '@microsoft/fetch-event-source';

export default {
  directives: {
    'delay-text': {
      inserted: function (el, binding) {
        let text = binding.value;
        let delay = 100; // 延迟时间

        let i = 0;
        let timer = setInterval(function () {
          if (i > text.length) {
            clearInterval(timer);
          } else {
            el.innerText = text.substring(0, i);
            i++;
          }
        }, delay);
      }
    }
  },
  data() {
    return {
      messages: [
        { id: 1, content: 'Hello!', author: 'User' },
        { id: 2, content: 'Hi there!', author: 'Agent' }
      ],
      newMessage: ''
    };
  },
  methods: {
    unescapeUnicode(str) {
      return str.replace(/\\u(\w\w\w\w)/g,function(a,b) {
        var charcode = parseInt(b,16);
        return String.fromCharCode(charcode);
      });
    },
    sendMessage() {
      if (this.newMessage.trim()) {
        const userId = this.messages.length + 1;
        this.messages.push({ userId, content: this.newMessage, author: 'User' });
        const that = this
        let msg = ''
        let param = {
          "message": this.newMessage
        }
        let url = `${process.env.VUE_APP_API}/ai/chat`
        const controller = new AbortController();
        fetchEventSource(url,{
          method: 'POST',
          signal: controller?.signal,
          headers: {
            "Content-Type": 'application/json',
            "Accept": 'text/event-stream'
          },
          body: JSON.stringify(param),
          onmessage(event) {
            // 在这里操作流式数据
            msg = msg.concat(event.data)
          },
          async onopen(response) {
            if (response.ok) {
              console.log('开始建立连接');
            }
          },
          onerror(error) {
            console.log('err', error);
          },
          onclose() {
            console.log('关闭');
            console.log(msg);
            const agentId = userId + 1
            that.messages.push({ userId: agentId, content: that.unescapeUnicode(msg), author: 'Agent' });
            controller?.abort();
            throw new Error();
          }
        }).catch((err) => {
          controller?.abort();
          console.log({ err });
          throw new Error(err);
        });
        this.newMessage = '';
      }
    }
  }
};
</script>

<style scoped>
.chat-container {
  width: 950px;
  height: 500px;
  margin: 50px auto;
  padding: 20px;
  background: #F6F6F6;
  border: 1px solid #ddd;
  border-radius: 5px;
}

.input-container {
  width: 950px;
  height: 60px;
  margin: 50px auto;
  padding: 20px;
  background: #FFFFFF;
  border-radius: 5px;
}

.chat-messages {
  height: 450px;
  overflow-y: scroll;
  padding: 10px;
}

.message {
  margin-bottom: 15px;
  padding: 10px;
  background: #ffffff;
  border-radius: 5px;
  width: calc(100% - 20px);
}

.message-content {
  margin-bottom: 5px;
  text-align: left;
}

.message-author {
  font-size: 12px;
  color: #999;
  text-align: right;
}

input[type="text"] {
  width: calc(100% - 60px);
  padding: 10px;
  border: 1px solid #ffffff;
  border-radius: 5px;
  font-size: 16px;
}

button {
  width: 60px;
  padding: 10px;
  background: #4e8ef2;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background: #386692;
}

form {
  border: 1px solid #ddd;
  width: 950px;
  display: flex;
  margin-top: 10px;
}
</style>
