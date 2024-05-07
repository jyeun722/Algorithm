import sys
input = sys.stdin.readline

H,W = map(int,input().split())
blocks = list(map(int,input().split()))
ans = 0

for i in range(1, W-1):
    left = max(blocks[:i])
    right = max(blocks[i+1:])

    less = min(left,right)

    if blocks[i] < less:
        ans += less - blocks[i]

print(ans)